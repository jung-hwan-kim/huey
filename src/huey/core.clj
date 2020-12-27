(ns huey.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.adapter.jetty :as ring]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.util.response :refer [response]]
            [ring.middleware.content-type :as c]
            [cheshire.core :as json]
            [clojure.tools.logging :as log]
            [clj-http.client :as client]
            ))

(defroutes app-routes
           (GET "/" request
             (log/info :uri "/" :request request)
             (response {:abc "test-abc"}))
           (POST "/debug" request
             (log/info request)
             (response {:uri (:uri request) :headers (:headers request)}))
           (route/not-found (response {:message "Page not found"})))
(def app
  (wrap-json-response app-routes))

(defn -main
  [& args]
  (ring/run-jetty #'app {:port 8080 :join? false}))
