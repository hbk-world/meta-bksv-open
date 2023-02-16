SUMMARY = "A free C++ class library of cryptographic schemes"
HOMEPAGE = "http://www.cryptopp.com/wiki/Main_Page"
BUGTRACKER = "http://sourceforge.net/apps/trac/cryptopp/"
SECTION = "libs"

LICENSE = "BSL-1.0"
LIC_FILES_CHKSUM = "file://License.txt;md5=deb6d182b0f7f8a866c42941b9f014c4"

BBCLASSEXTEND = "native nativesdk"

PR = "r1"

PVSHORT = "${@'${PV}'.replace('.','')}"
SRC_URI = " \
    https://github.com/weidai11/cryptopp/archive/CRYPTOPP_6_1_0.zip \
     \
"
S = "${WORKDIR}/cryptopp-CRYPTOPP_6_1_0/"
SRC_URI[md5sum] = "980281622453484cfe9370bf02b2e3a4"
SRC_URI[sha256sum] = "24fbd85bbd126f46540e0fe4ca6ae153936dda3a32c5ea3af2dd7d33f7dac5a4"


inherit autotools-brokensep pkgconfig

PACKAGES_prepend = "${PN}-test "

EXTRA_OECONF = "--libdir=${base_libdir}"
TARGET_CC_ARCH += "${LDFLAGS}"
export PREFIX="${prefix}"


do_compile() {
    sed -i -e 's/^CXXFLAGS/#CXXFLAGS/' GNUmakefile
    export CXXFLAGS="${CXXFLAGS} -DNDEBUG -fPIC"
    oe_runmake all libcryptopp.so
}

# do not provide the shared object file, so we force to link statically for host tools
do_compile_class-native() {
    sed -i -e 's/^CXXFLAGS/#CXXFLAGS/' GNUmakefile
    export CXXFLAGS="${CXXFLAGS} -DNDEBUG -fPIC"
    oe_runmake all
}

FILES_${PN}-test = " \
    ${bindir} \
    ${datadir}/cryptopp \
"