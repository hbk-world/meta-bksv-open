SUMMARY = "Linux Kernel provided and supported by bksv"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
inherit kernel 
inherit externalsrc
# require recipes-kernel/linux/linux-dtb.inc
require recipes-kernel/linux/linux-mfgtool.inc


PV = "4.9.11"
PR = "r0"

EXTERNALSRC = "${BSPDIR}/sources/linux-bksv"

COMPATIBLE_MACHINE = "(mx7|imx7dsabresd-bksv|sxu-g1)"

# We need to pass it as param since kernel might support more then one
# machine, with different entry points
KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

FILESEXTRAPATHS_append := "${THISDIR}/${PN}:"
SRC_URI_append = " file://defconfig "
