FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-Configure-http-and-web-xi-services.patch \
            file://0001-Add-TXT-record-type.patch \
            file://0001-Fix-segfault-when-txt-record-value-has-zero-length.patch \
            "

