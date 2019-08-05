SUMMARY = "Small configuration file parser library for C"
DESCRIPTION = ""
HOMEPAGE = "https://github.com/martinh/libconfuse"
SECTION = "devel"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=42fa47330d4051cd219f7d99d023de3a"

DEPENDS = "flex-native gettext"

SRC_URI = "https://github.com/martinh/libconfuse/releases/download/v${PV}/confuse-${PV}.tar.gz"

SRC_URI[md5sum] = "1ba3b410d8d46f6e74b0d37b4177e13f"
SRC_URI[sha256sum] = "71316b55592f8d0c98924242c98dbfa6252153a8b6e7d89e57fe6923934d77d0"

inherit autotools-brokensep lib_package gettext

BBCLASSEXTEND = "native nativesdk"

S = "${WORKDIR}/confuse-${PV}"
