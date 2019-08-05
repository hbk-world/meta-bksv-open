FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-Remove-battery-profiles-remote-read.patch \
            file://0001-Restart-bluetooth-service-on-failure.patch \
            file://main.conf \
            file://0001-Prevent-use-after-free-when-specifying-adv-timeout-o.patch \
            "

FILES_${PN} += "${sysconfdir}/bluetooth/main.conf"

do_install_append() {
        install -m 0644 ${WORKDIR}/main.conf ${D}/${sysconfdir}/bluetooth
}