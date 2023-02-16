LICENSE = "CLOSED"
SRC_URI = "\
				file://dhparam.pem \
				file://create-self-certs.sh \
				file://setup-selfsigning.service \					
				file://self-cert.conf \
"

FILES_${PN} =  "${datadir}/certs/self-cert.conf \
 				${systemd_unitdir}/system/setup-selfsigning.service \
 				${sysconfdir}/ssl/certs/dhparam.pem \
 				${bindir}/create-self-certs.sh \
 				"

SYSTEMD_SERVICE_${PN} += "setup-selfsigning.service"
inherit systemd sca
RDEPENDS_${PN} += "bash"
do_install_append() {
    install -d ${D}${sysconfdir}/ssl/certs
    install -d ${D}${bindir}
    install -d ${D}${datadir}/certs
    install -d ${D}${systemd_unitdir}/system

    install -m 0444 ${WORKDIR}/dhparam.pem ${D}${sysconfdir}/ssl/certs/dhparam.pem
    
    install -m 0744 ${WORKDIR}/create-self-certs.sh ${D}${bindir}/create-self-certs.sh
    install -m 0444 ${WORKDIR}/self-cert.conf ${D}${datadir}/certs/self-cert.conf
    install -m 0444 ${WORKDIR}/setup-selfsigning.service ${D}${systemd_unitdir}/system/setup-selfsigning.service

}