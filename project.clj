(defproject huey "0.1.0-SNAPSHOT"
  :description "Huey"
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [io.pedestal/pedestal.service "0.5.9"]
                 [io.pedestal/pedestal.jetty "0.5.9"]
                 [org.clojure/tools.logging "1.1.0"]
                 [clj-http "3.10.3"]]
  :repl-options {:init-ns huey.core}
  :resource-paths ["config", "resources"]
  :profiles {:dev {:aliases {"run-dev" ["trampoline" "run" "-m" "huey.core/run-dev"]}
                   :dependencies [[io.pedestal/pedestal.service-tools "0.5.9"]]}}
  :main ^:skip-aot huey.core)
