LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = "file://post_update.sh \
				  file://post_update.service \
"

inherit systemd
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "post_update.service"

RDEPENDS_${PN} += "bash"

do_install_append() {
    install -d ${D}${bindir}
    install -m 0744 ${WORKDIR}/post_update.sh ${D}${bindir}

	install -d ${D}${systemd_unitdir}
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/post_update.service ${D}${systemd_unitdir}/system
}
