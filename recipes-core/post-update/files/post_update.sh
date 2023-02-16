#!/bin/bash

#
# post_update.sh
#
# Intended to run on the first boot after a firmware update.
# 
# Expands the new root filesystem, if required.
#
# Makes a best-effort attempt to transfer files and settings
# from the old rootfs to the new/current rootfs.
#

echo "$0"

# Ensure this script only runs once after firmware installation.
# systemd should be configured to only run this script if
# /var/post_update does not exist
touch /var/post_update

# Find out which device the new/current rootfs is mounted on
CUR_ROOT_DEV=`findmnt --target / | awk 'END{print $2}'`

# The old rootfs will be "the other device" as documented in Confluence
if [[ $CUR_ROOT_DEV = "/dev/mmcblk2p5" ]]; then
    OLD_ROOT_DEV="/dev/mmcblk2p6"
elif [[ $CUR_ROOT_DEV = "/dev/mmcblk2p6" ]]; then
    OLD_ROOT_DEV="/dev/mmcblk2p5"
else
    echo "Unexpected rootfs device \"$CUR_ROOT_DEV\""
    exit 1
fi

echo "Current rootfs device is $CUR_ROOT_DEV, old rootfs device is $OLD_ROOT_DEV"

echo "Resizing current rootfs to maximum size"
resize2fs $CUR_ROOT_DEV

echo "Mounting old rootfs"
MNT_PATH=/mnt/old_rootfs
mkdir -p "$MNT_PATH"
mount "$OLD_ROOT_DEV" "$MNT_PATH"

# Production scenario; there may not be a previous installation, so
# check for the existence of /etc on the old rootfs before we start
# copying files.
if [ ! -d "${MNT_PATH}/etc" ]; then
    echo "Old rootfs not found (production scenario?)"
else
    echo "Transferring files and settings"

    cp -av "${MNT_PATH}/etc/localtime" /etc/localtime
fi

echo "Unmounting old rootfs"
umount "$MNT_PATH"
rm -r "$MNT_PATH"

echo "Done"
exit 0
