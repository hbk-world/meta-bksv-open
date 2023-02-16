FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"



# Copyright (C) 2012-2016 O.S. Systems Software LTDA.
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-bsp/u-boot/u-boot.inc
SRC_URI = ""
inherit externalsrc sca
EXTERNALSRC = "${BSPDIR}/sources/u-boot-fslc"
inherit fsl-u-boot-localversion

DESCRIPTION = "U-Boot based on bksv sources."
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"
COMPATIBLE_MACHINE = "(mxs|mx5|mx6|mx7|vf)"

DEPENDS_mxs += "elftosb-native openssl-native"

PROVIDES += "u-boot"

# FIXME: Allow linking of 'tools' binaries with native libraries
#        used for generating the boot logo and other tools used
#        during the build process.
EXTRA_OEMAKE += 'HOSTCC="${BUILD_CC} ${BUILD_CPPFLAGS}" \
                 HOSTLDFLAGS="${BUILD_LDFLAGS}" \
                 HOSTSTRIP=true'

