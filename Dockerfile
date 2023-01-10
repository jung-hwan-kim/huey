FROM clojure
LABEL huey.project=prototype
ENV PORT 40000
ARG APP_DIR=/app

COPY . $APP_DIR
WORKDIR $APP_DIR
RUN lein compile
EXPOSE $PORT
CMD lein repl :headless :port $PORT
