SUMMARY = "Laird Wi-Fi Backport"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit module lrd-url

DEPENDS += "coreutils-native"
RDEPENDS_${PN} += "laird-sterling60-firmware-sdio-sdio"

SRC_URI = "${LRD_URI_BASE}/${PN}-${PV}.tar.bz2 \
           file://0001-Adjust-conn-min-max-interval-for-iOS-compatibility.patch \
           file://0001-Avoid-printing-message-on-error-level.patch \
           file://0001-Disable-VHT.patch \
           file://0001-Disable-5GHz.patch \
           file://0001-Lock-fwcmd-when-cleaning-up-sdio.patch \
           file://0001-SLX-417-Fix-race-with-bluetooth-driver.patch \
           file://0002-Suppress-debug-messages.patch \
           file://0002-Do-not-schedule-rx_task-tasklet-when-rx_task-is-disa.patch \
           file://lrdmwl.conf \
           "

SRC_URI[md5sum] = "fe943430cb4bc788d12d45829bc64314"
SRC_URI[sha256sum] = "a4c0fec2e1c3a05b8111aec31bf4274bb5dd5c6c405613e73e0fc4d3090787ef"

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
