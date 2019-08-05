FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = "file://NetworkManager.conf \
                  file://create-syscon.sh \
                  file://create-syscon.service \
				  file://0001-Select-ipv6-addr-gen-mode-eui64-by-default.patch \
"

FILES_${PN} += "${sysconfdir}/NetworkManager/NetworkManager.conf \
                ${bindir}/create-syscon.sh \
"

SYSTEMD_SERVICE_${PN}_append = " create-syscon.service"

RDEPENDS_${PN} += "bash"

do_install_append(){
	install -d ${D}/${sysconfdir}
	install -d ${D}/${sysconfdir}/NetworkManager
	install -m 0755 ${WORKDIR}/NetworkManager.conf ${D}/${sysconfdir}/NetworkManager/NetworkManager.conf

    # The system-connections folder is not part of the rootfs,
	# so add a script to create it at runtime
    install -m 0544 ${WORKDIR}/create-syscon.sh ${D}${bindir}
	install -d ${D}${systemd_unitdir}
	install -d ${D}${systemd_unitdir}/system
	install -m 0444 ${WORKDIR}/create-syscon.service ${D}${systemd_unitdir}/system
}
