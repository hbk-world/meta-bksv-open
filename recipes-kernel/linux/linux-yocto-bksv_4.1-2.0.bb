inherit kernel
inherit externalsrc
require recipes-kernel/linux/linux-yocto.inc

EXTERNALSRC = "${BSPDIR}/sources/linux-bksv"
PV = "4.9.84"
PR = "r0"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " file://defconfig "

COMPATIBLE_MACHINE = "(imx7dsabresd-bksv|sxu-g1)"
