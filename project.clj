(defproject huey "0.1.0-SNAPSHOT"
  :description "Huey"
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/tools.logging "1.1.0"]
                 [environ "1.2.0"]
                 [clj-http "3.12.2"]]
  :repl-options {:init-ns huey.core}
  :resource-paths ["config", "resources"]
  :profiles {:dev {:aliases {"run-dev" ["trampoline" "run" "-m" "huey.core/run-dev"]}
                   :dependencies [[io.pedestal/pedestal.service-tools "0.5.9"]]}}
  :main ^:skip-aot huey.core)
