#!/usr/bin/env bash
#
# Based on https://github.com/raamsri/automount-usb
#
# If you are executing this script in cron with a restricted environment,
# modify the shebang to specify appropriate path; /bin/bash in most distros.
# And, also if you aren't comfortable using(abuse?) env command.

# This script is based on https://serverfault.com/a/767079 posted
# by Mike Blackwell, modified to our needs. Credits to the author.

# This script is called from systemd unit file to mount or unmount
# a USB drive.

PATH="$PATH:/usr/bin:/usr/local/bin:/usr/sbin:/usr/local/sbin:/bin:/sbin"
log="logger -t usb-mount.sh -s "

usage()
{
    ${log} "Usage: $0 {add|remove} device_name (e.g. sdb1)"
    exit 1
}

if [[ $# -ne 2 ]]; then
    usage
fi

ACTION=$1
DEVBASE=$2
DEVICE="/dev/${DEVBASE}"

${log} "$ACTION $DEVBASE $DEVICE"

# See if this drive is already mounted, and if so where
MOUNT_POINT=$(mount | grep ${DEVICE} | awk '{ print $3 }')

do_mount()
{
    if [[ -n ${MOUNT_POINT} ]]; then
        ${log} "Warning: ${DEVICE} is already mounted at ${MOUNT_POINT}"
        exit 0
    fi

    # Get info for this drive: $ID_FS_LABEL and $ID_FS_TYPE
    eval $(udevadm info --query=property --name=${DEVBASE} | grep -i -e "ID_FS_LABEL" -e "ID_FS_TYPE")

    ${log} "Label '${ID_FS_LABEL}' type '${ID_FS_TYPE}'"

    MOUNT_POINT="/media/${DEVBASE}"

    ${log} "Mount point: ${MOUNT_POINT}"

    mkdir -p ${MOUNT_POINT}

    # Global mount options
    OPTS="rw,relatime"

    # File system type specific mount options
    if [[ ${ID_FS_TYPE} == "vfat" || ${ID_FS_TYPE} == "exfat" ]]; then
        OPTS+=",users,gid=100,umask=000,shortname=mixed,utf8=1,flush"
    fi

    if ! mount -o ${OPTS} ${DEVICE} ${MOUNT_POINT}; then
        # To support devices with filesystems but without partition tables, we
        # always attempt to mount the block device, i.e. /dev/sda.
        # This will fail in cases where the device does have a partition table,
        # so just print a warning but don't indicate error.
        ${log} "Could not mount ${DEVICE} (status = $?)"
        rmdir "${MOUNT_POINT}"
        exit 0
    fi

    ${log} "Mounted ${DEVICE} at ${MOUNT_POINT}"
}

do_unmount()
{
    if [[ -z ${MOUNT_POINT} ]]; then
        ${log} "Warning: ${DEVICE} is not mounted"
    else
        umount -l ${DEVICE}
        ${log} "Unmounted ${DEVICE} from ${MOUNT_POINT}"
    fi

    MOUNT_POINT="/media/${DEVBASE}"
    ${log} "Removing ${MOUNT_POINT}"
    /bin/rmdir "${MOUNT_POINT}"
}

case "${ACTION}" in
    add)
        do_mount
        ;;
    remove)
        do_unmount
        ;;
    *)
        usage
        ;;
esac

exit 0
