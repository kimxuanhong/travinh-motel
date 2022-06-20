echo ==== Checkout SCM ====
git pull origin

echo ==== Build jar file ====
mvn clean install -DskipTests

echo ==== Build images docker ====
docker build -t travinh-motel:latest .

echo ==== SUCCESS =====
