(ns huey.service
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [clj-http.client :as client]
            [clojure.tools.logging :as log]
            [cheshire.core :as json]
            ))

(defn hello-world
  [request]
  (let [name (get-in request [:params :name] "World")]
    {:status 200 :body (str "Hello " name "!\n")}))

(defn abc [request]
  {:status 200 :body "test\n"}
  )

(defroutes routes
           [[["/"
              ["/hello" {:get hello-world}]
              ["/abc" {:get abc}]
              ]]])

(def service {:env                 :prod
              ::http/routes        routes
              ::http/resource-path "/public"
              ::http/type          :jetty
              ::http/port          8080})
