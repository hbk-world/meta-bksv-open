##### rndis.bb ######
SUMMARY = "load g_ether kernel module at boot to support RNDIS (TCP over USB)"
SECTION = "networking"
LICENSE = "CLOSED"

inherit sca

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI = "file://usb-gether \
           file://libcomposite.conf"

S = "${WORKDIR}"

do_install_append() {
	install -d ${D}${base_sbindir}
	install -m 0755 usb-gether ${D}${base_sbindir}

	install -d ${D}${sysconfdir}/modules-load.d
	install libcomposite.conf ${D}${sysconfdir}/modules-load.d
}
