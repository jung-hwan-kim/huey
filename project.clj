(defproject huey "0.1.0-SNAPSHOT"
  :description "Huey"
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/tools.logging "1.1.0"]
                 [environ "1.2.0"]
                 [cheshire "5.11.0"]
                 [org.clojure/data.csv "1.0.1"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [org.postgresql/postgresql "42.5.0"]
                 [clj-commons/clj-yaml "1.0.26"]
                 [com.azure/azure-storage-blob "12.14.4"]
                 [clojure.java-time "1.1.0"]
                 [com.hierynomus/sshj "0.34.0"]
                 [com.github.mwiede/jsch "0.2.6"]
                 [io.github.nextjournal/clerk "0.12.707"]
                 [com.google.cloud/google-cloud-storage "2.15.1"]
                 [clj-http "3.12.3"]]
  :repl-options {:init-ns huey.repl}
  :java-source-paths ["main/java"]
  :resource-paths ["config", "resources"]
  :profiles {:dev {:aliases {"run-dev" ["trampoline" "run" "-m" "huey.core/run-dev"]}
                   :dependencies [[io.pedestal/pedestal.service-tools "0.5.9"]]}}
  :main ^:skip-aot huey.core)
