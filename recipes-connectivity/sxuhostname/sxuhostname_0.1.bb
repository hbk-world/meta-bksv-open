LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = "file://set-hostname.sh \
		   file://sxuhostname.service \
		"
inherit systemd sca
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "sxuhostname.service"
RDEPENDS_${PN} +="bash"

do_install_append() {
    install -d ${D}${bindir}
    install -m 0744 ${WORKDIR}/set-hostname.sh ${D}${bindir}/set-hostname.sh
	install -d ${D}${systemd_unitdir}
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/sxuhostname.service ${D}${systemd_unitdir}/system
}
