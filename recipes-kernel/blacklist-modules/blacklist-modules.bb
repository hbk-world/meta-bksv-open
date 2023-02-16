SUMMARY = "Blacklisting of driver modules"
SECTION = "core"
LICENSE = "CLOSED" 

inherit sca

SRC_URI = "\
    file://blacklist.conf \
"

do_install() {
    install -d ${D}/${sysconfdir}/modprobe.d/
    install -m 0644 ${WORKDIR}/blacklist.conf ${D}/${sysconfdir}/modprobe.d
}

