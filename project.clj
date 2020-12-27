(defproject huey "0.1.0-SNAPSHOT"
  :description "Huey"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [compojure "1.6.2"]
                 [ring/ring-jetty-adapter "1.8.1"]
                 [ring/ring-json "0.5.0"]
                 [org.clojure/tools.logging "1.1.0"]
                 [clj-http "3.10.3"]]
  :repl-options {:init-ns huey.core}
  :main ^:skip-aot huey.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
