(ns huey.service
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [clojure.tools.logging :as log]
            [cheshire.core :as json]
            [io.pedestal.http.route.definition :refer [defroutes]]
            [clj-http.client :as client]))

(defn hello-world
  [request]
  (let [name (get-in request [:params :name] "World")]
    {:status 200 :body (str "Hello " name "!\n")}))

(defn abc [request]
  {:status 200
   ;:content-type :json
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string {:msg "hello" :status "ok"})}
  )

(defn logs [request]
  (println (json/parse-stream (clojure.java.io/reader (:body request)) true))
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string {:msg "hello" :status "ok"})}
  )

;(json/decode (:body (client/get "http://localhost:8888/abc" {:accept :json})))


(defroutes routes
           [[["/"
              ["/hello" {:get hello-world}]
              ["/abc" {:get abc}]
              ["/logs" {:post logs}]
              ]]])

(def service {:env                 :prod
              ::http/routes        routes
              ::http/resource-path "/public"
              ::http/type          :jetty
              ::http/port          8888})
