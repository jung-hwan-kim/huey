---
name: Release 🕊️
about: Release a new version of Rubicore/DQF.
title: ''
labels: 'release,documentation'
assignees: ''

---

## Rubicore/DQF vx.x.x Release

_Any additional notes about the release goes here_

- On-call :astronaut:     :     
- Fixes/Features :muscle: : https://github.com/rubikloud/rubicore/milestone/22
- Code Freeze :date:      : July 16th 2020

Links to individual components tags that are going to this release.

component|version
---------|--------
rc-orchestrator| [v0.1.0](https://github.com/rubikloud/rc-orchestrator/releases/tag/v0.1.0)
rc-apiserver |
rc-scheduler |
rc-operator | 
rc-cluster-sdk |
rc-stuka |
danube |
missingno |
rubicore-helm |

## Release process checklist

- [ ] Create a vx.x.xrc1 tag for rubicore-helm and dqf-helm.
- [ ] `helm install/upgrade` on a clean K8s environment is successful in GCP (rk-cac-dev)
- [ ] `helm install/upgrade` on a clean K8s environment is successful in Azure (rk-azcac-dev)
- [ ] E2E test of a normal case scenario for adhoc using valid Metafiles passed on GCP (rk-cac-dev)
- [ ] E2E test of a normal case scenario for adhoc using valid Metafiles passed on Azure (rk-azcac-dev)
- [ ] E2E test of a normal case scenario for scheduled Run using valid Metafiles passed on GCP (rk-cac-dev)
- [ ] E2E test of a normal case scenario for scheduled Run using valid Metafiles passed on Azure (rk-azcac-dev)
- [ ] Tests specific to features/bugfixes have passed on Azure & GCP. (Individual owners for the related tickets are equally responsible in the testing)
- [ ] If and when tests pass, create a final release of vx.x.x. Make sure the description is consistent and in-line with previous release notes.

## Release deployments to Client environments {if any):

