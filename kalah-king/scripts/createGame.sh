#!/bin/bash

if [ $# -ne 1 ]; then
    echo usage: "$0" "[URI: e.g. localhost:8080]"
    exit 1
fi

HOST=${1}
HTTP_METHOD=POST

function createGame {
     curl -s -k -X "${HTTP_METHOD}" http://"${HOST}"/games \
        --header "Content-Type: application/json"
}

RESPONSE=$(createGame)
echo RESPONSE: "${RESPONSE}"