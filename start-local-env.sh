cd docker || exit;

docker compose down
docker-compose up -d

cd ..

echo "waiting for DB up"

function getMysqlHealth {
  docker inspect --format "{{.State.Health.Status}}" docker-refundly-db-1
}

function waitContainer {
  while STATUS=$(getMysqlHealth); [ "$STATUS" != "healthy" ]; do
    if [ "$STATUS" == "unhealthy" ]; then
      echo "Failed to load mysql container!"
      exit 1;
    fi
    printf .
    sleep 0.5
  done
  printf "\n"
}

waitContainer

