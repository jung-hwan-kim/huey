(ns huey.client
  (:require [clj-http.client :as client]
            [cheshire.core :as json]))

(-> "http://localhost:8888/abc"
    (client/get {:accept :json})
    :body
    (json/decode true {:pretty true}))
