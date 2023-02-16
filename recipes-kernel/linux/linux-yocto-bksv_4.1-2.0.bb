inherit kernel
inherit externalsrc
inherit sca
require recipes-kernel/linux/linux-yocto.inc

EXTERNALSRC = "${BSPDIR}/sources/linux-bksv"
PV = "4.14.126"
PR = "r0"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " file://defconfig "

COMPATIBLE_MACHINE = "(imx7dsabresd-bksv|sxu-g1)"
KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"
