FROM clojure
LABEL huey.version=1.1
ENV PORT 8080

COPY . /app
WORKDIR /app
EXPOSE $PORT
CMD lein run
