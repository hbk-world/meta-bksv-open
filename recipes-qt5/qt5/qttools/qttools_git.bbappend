FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " \
    file://0003-linguist-tools-cmake-allow-overriding-the-location-f.patch \
"
CMAKE_ALIGN_SYSROOT[2] = "ignore"