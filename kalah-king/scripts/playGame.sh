#!/bin/bash

if [ $# -ne 3 ]; then
    echo usage: "$0" "[URI: e.g. localhost:8080] [GAME_ID eg: xyz-abc] [PIT: Between 1-6 or 8-13]"
    exit 1
fi

HOST=${1}
HTTP_METHOD=PUT
GAME_ID=${2}
PIT_ID=${3}

function playGame {
     curl -s -k -X "${HTTP_METHOD}" http://"${HOST}"/games/"${GAME_ID}"/pits/"${PIT_ID}" \
        --header "Content-Type: application/json"
}

RESPONSE=$(playGame)
echo RESPONSE: "${RESPONSE}"