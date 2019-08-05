SUMMARY = "Configurable embedded Linux firmware update creator and runner"
DESCRIPTION = ""
HOMEPAGE = "https://github.com/fhunleth/fwup"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"
DEPENDS = "libconfuse libarchive libsodium"
RDEPENDS_${PN}-target = "mmc-utils dosfstools util-linux"
RDEPENDS_${PN} += "bash"

SRC_URI = "git://github.com/fhunleth/fwup;protocol=https \
           file://0001-Implement-minimum-version-parameter.patch \
           file://0002-Implement-mmc_writebootpart.patch \
           file://0003-Implement-mmc_setbootpart.patch \
           file://0004-Implement-fs_mkvfat.patch \
           file://0005-Implement-fs_writefile.patch \
           file://0006-Implement-part_write.patch \
           file://0001-SLX-523-SLX-593-Ensure-block-cache-is-flushed-before.patch \
           "
PV = "1.2.7+git${SRCPV}"
SRCREV = "3546a5880d938197f1e1d094fd3b709a115bdf71"
S = "${WORKDIR}/git"

inherit autotools pkgconfig
FILES_${PN} += "${datadir}/bash-completion/completions/fwup \
               ${bindir}/fwup \
"
PACKAGES = "${PN}-dev ${PN}-dbg ${PN}"
BBCLASSEXTEND = "native nativesdk"
