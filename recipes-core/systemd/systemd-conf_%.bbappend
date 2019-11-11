
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://journald.conf \
            file://00-create-volatile.conf \
"

do_install_append() {
    install -m 0644 ${WORKDIR}/journald.conf ${D}${sysconfdir}/systemd

    # Disable script to set NOCOW attributes on journal folders.
    # Only relevant for btrfs filesystems and generates an error during start-up.
    # rm -f ${D}${libdir}/tmpfiles.d/journal-nocow.conf
    # ln -s /dev/null ${D}${libdir}/tmpfiles.d/journal-nocow.conf
}
