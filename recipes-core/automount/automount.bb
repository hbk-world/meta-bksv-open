LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = "file://99-automount.rules \
                  file://automount.sh \
                  file://automount@.service \
"

inherit systemd
SYSTEMD_PACKAGES = "automount"
SYSTEMD_SERVICE_automount = "automount@.service"

do_install_append() {
    install -d ${D}/${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/99-automount.rules ${D}/${sysconfdir}/udev/rules.d

    install -d ${D}${bindir}
    install -m 0744 ${WORKDIR}/automount.sh ${D}${bindir}

	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/automount@.service ${D}${systemd_unitdir}/system
}
