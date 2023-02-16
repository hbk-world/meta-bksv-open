FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://KF5NetworkManagerQtTargets.cmake"

do_configure_append(){
    # Fixes the INTERFACE_INCLUDE_DIRECTORIES definition for the SDK
    cp ${WORKDIR}/KF5NetworkManagerQtTargets.cmake ${B}/CMakeFiles/Export/lib/cmake/KF5NetworkManagerQt/
}
