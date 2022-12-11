#!/bin/bash
PID=${sudo pgrep -f SNAPSHOT}
echo "id: ${PID} detected"
if [[ -n ${PID} ]]; then
  sudo kill "${PID}"
fi
