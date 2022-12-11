#!/bin/bash
PID=$(pgrep -f SNAPSHOT.jar)
if [[ -n ${PID} ]]; then
    echo "killing the previous app running..."
    sudo kill "${PID}"
fi
