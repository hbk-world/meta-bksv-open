# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "0mq 'highlevel' C++ bindings"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
# NOTE: spec file indicates the license may be "LGPLv3"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

SRC_URI = "git://github.com/zeromq/zmqpp.git;protocol=https;branch=develop"
PACKAGES = "${PN} ${PN}-dev" 

# Modify these as desired
PV = "1.1+git${SRCPV}"
SRCREV = "a848672af6a971be6da4db9aeece783b5fd99131"

S = "${WORKDIR}/git"

DEPENDS = "boost zeromq"

inherit cmake

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = "-DZMQPP_BUILD_STATIC=YES \
	         -DZMQPP_BUILD_SHARED=YES \
		 -DZMQPP_BUILD_CLIENT=NO \
		 -DZMQPP_BUILD_EXAMPLES=NO \
		 -DZMQPP_BUILD_TESTS=NO \
"
FILES_${PN} = "${libdir}/libzmqpp.so"
FILES_${PN}-dev = "${includedir}" 
FILES_${PN}-dbg = "/usr/src/"
