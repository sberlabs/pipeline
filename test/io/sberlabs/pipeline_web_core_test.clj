(ns io.sberlabs.pipeline-web-core-test
  (:require [clojure.test :refer :all]
            [io.sberlabs.pipeline-service :as hello-svc]
            [io.sberlabs.pipeline-web-core :refer :all]
            [ring.mock.request :as mock]))

(deftest hello-web-test
  (testing "says hello to caller"
    (let [hello-service (reify hello-svc/HelloService
                          (hello [this caller] (format "Testing, %s." caller)))
          ring-app (app hello-service)
          response (ring-app (mock/request :get "/foo"))]
      (is (= 200 (:status response)))
      (is (= "Testing, foo." (:body response))))))
