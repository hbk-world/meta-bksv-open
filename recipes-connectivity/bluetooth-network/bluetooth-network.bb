SUMMARY = "Start bluetooth network"
LICENSE = "CLOSED"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://main.conf \
			file://br.sh     \
			file://test-nap  \
			file://bluezutils.py \
			file://bluetooth-network.service \
"
inherit systemd sca
DEPENDS = "bluez5"
RDEPENDS_${PN} = "python bash"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "bluetooth-network.service"

FILES_${PN} += "${systemd_unitdir}/systemd/system/bluetooth-network.service \
				 ${bindir}/br.sh ${bindir}/bluezutils.sh ${bindir}/test-nap \
 				 ${sysconfdir}/bluetooth/main.conf"

do_install_append() {
	install -d ${D}${systemd_unitdir}
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/bluetooth-network.service ${D}${systemd_unitdir}/system

	install -d ${D}${bindir}
	install -d ${D}${sysconfdir}/bluetooth
	install -m 0644 ${WORKDIR}/main.conf ${D}${sysconfdir}/bluetooth
	install -m 0744 ${WORKDIR}/br.sh ${D}${bindir}
	install -m 0744 ${WORKDIR}/test-nap ${D}${bindir}
	install -m 0744 ${WORKDIR}/bluezutils.py ${D}${bindir}
}