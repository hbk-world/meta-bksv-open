SUMMARY = "Laird Sterling 60 Firmware"
SECTION = "kernel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch lrd-url sca

SRC_URI += "${LRD_URI_BASE}/${PN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "848265dad96ec9794374d928f71156ec"
SRC_URI[sha256sum] = "0f34e363d631b3c324834e989e801e141cea4a916e756f6749a2253edcc60722"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES_${PN} += "${nonarch_base_libdir}/*"

S = "${WORKDIR}"

do_install() {
	install -d  ${D}${nonarch_base_libdir}
	cp -r ${S}/lib/* ${D}${nonarch_base_libdir}
}
