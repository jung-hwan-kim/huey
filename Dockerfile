FROM clojure
LABEL huey.project=prototype
ENV PORT 8080
ARG APP_DIR=/app

COPY . $APP_DIR
WORKDIR $APP_DIR
EXPOSE $PORT
CMD lein run
