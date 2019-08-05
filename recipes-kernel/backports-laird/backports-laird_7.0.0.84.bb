SUMMARY = "Laird Wi-Fi Backport"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit module lrd-url

DEPENDS += "coreutils-native"
RDEPENDS_${PN} += "laird-sterling60-firmware-sdio-sdio"

SRC_URI = "${LRD_URI_BASE}/${PN}-${PV}.tar.bz2 \
           file://0001-SLX-417-Fix-race-with-bluetooth-driver.patch \
           file://0002-Suppress-debug-messages.patch \
           file://0001-Lock-fwcmd-when-cleaning-up-sdio.patch \
           file://0001-Disable-VHT.patch \
           file://0001-Disable-5GHz.patch \
           file://0001-Supervision-timeout-5s-for-iOS-compatibility.patch \
           file://0001-Adjust-conn-min-max-interval-for-iOS-compatibility.patch \
           file://0001-Avoid-printing-message-on-error-level.patch \
           file://0002-Do-not-schedule-rx_task-tasklet-when-rx_task-is-disa.patch \
           file://lrdmwl.conf \
           "

SRC_URI[md5sum] = "e1bdb2f83fa86acd65c5f8ee019db8d4"
SRC_URI[sha256sum] = "194a3546beff23f17b0cba41f9ac472d594be2cc2e80f6f2d19cb6acb86434cf"

S = "${WORKDIR}/laird-backport-${PV}"

EXTRA_OEMAKE = "KLIB_BUILD=${STAGING_KERNEL_DIR} KLIB=${D} DESTDIR=${D} KMODDIR=. KERNEL_CONFIG=${STAGING_KERNEL_BUILDDIR}/.config"

do_compile_prepend() {
	rm -f ${S}/.kernel_config_md5
	oe_runmake CC=${BUILD_CC} defconfig-sterling60
}

do_install_append() {
	install -d ${D}${sysconfdir}/modprobe.d
	install ${WORKDIR}/lrdmwl.conf ${D}${sysconfdir}/modprobe.d
}
