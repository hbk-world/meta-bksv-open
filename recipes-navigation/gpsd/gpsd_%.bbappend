
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI_append = "file://gpsd.default "
SRC_URI += "file://0001-added-pre_gpsd-and-post_gpsd-commands.patch \
            file://0002-Start-gpsd-before-application.patch \
"

SYSTEMD_SERVICE_${PN} += "${PN}.service"

do_install_append() {
    install -m 0644 ${WORKDIR}/gpsd.default ${D}/${sysconfdir}/default/gpsd.default
}


