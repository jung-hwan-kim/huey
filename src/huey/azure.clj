(ns huey.azure
  (:require
    [java-time.api :as jt])
  (:use [huey.core])
  (:import (com.azure.core.util BinaryData)
           (huey AzureBlobService)))


(defn cat[container_name blob_name]
  (AzureBlobService/getContent container_name blob_name))

(defn create[content-str container-name blob-name]
  (AzureBlobService/uploadContent content-str container-name blob-name false))

(defn update[content-str container-name blob-name]
  (AzureBlobService/uploadContent content-str container-name blob-name true))


(defn ls-containers[]
  (->> (.listBlobContainers (AzureBlobService/getBlobServiceClient))
       .iterator
       iterator-seq
       (map bean)))

(defn blob-item->map[blob-item]
  (-> blob-item
      bean
      ((fn [x] (merge x (bean (:properties x)))))
      (select-keys [:creationTime :blobType :ETag :name :lastModified :contentLength :contentType])))


(defn ls
  ([container-name]
   (->> (.listBlobs (AzureBlobService/getBlobContainerClient container-name))
        .iterator
        iterator-seq
        (map blob-item->map)))
  ([container-name dir]
    (->> (.listBlobsByHierarchy (AzureBlobService/getBlobContainerClient container-name) dir)
         .iterator
         iterator-seq
         (map blob-item->map))))

(defn generate-sftp-heartbeat[sftp root-dir]
  (let [heartbeat (generate-heartbeat-data)
        dir (str root-dir "/" (:dir heartbeat))]
    (if (.exists sftp dir)
      (prn (str dir " already exist"))
      (do
        (prn (str "Creating " dir))
        (.mkdirRecursively sftp dir)))
    (.put sftp (:content heartbeat) (str dir "/" (:filename heartbeat)))))
