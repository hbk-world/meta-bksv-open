
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://journald.conf \
            file://00-create-volatile.conf \
            file://0001-SLX-664-Work-around-mount-error.patch \
            "

do_install_append() {
    install -m 0644 ${WORKDIR}/journald.conf ${D}${sysconfdir}/systemd

    # Disable script to set NOCOW attributes on journal folders.
    # Only relevant for btrfs filesystems and generates an error during start-up.
    rm -f ${D}${libdir}/tmpfiles.d/journal-nocow.conf
    ln -s /dev/null ${D}${libdir}/tmpfiles.d/journal-nocow.conf

    # Disable coredump service by default.
    # Application devs can re-enable coredump by rm'ing this link.
    ln -s /dev/null ${D}${sysconfdir}/systemd/system/systemd-coredump.socket

    # Prevent dhcp-server from starting automatically.
    # Application software manages the DHCP server manually.
    ln -s /dev/null ${D}${sysconfdir}/systemd/system/dhcp-server.service
}

PACKAGECONFIG_append = "coredump"
