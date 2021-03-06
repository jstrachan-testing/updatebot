/*
 * Copyright 2016 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package io.fabric8.updatebot.model;

import io.fabric8.utils.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class Projects {
    private GitHubProjects github;
    private List<GitRepository> git = new ArrayList<>();

    public GitHubProjects getGithub() {
        return github;
    }

    public void setGithub(GitHubProjects github) {
        this.github = github;
    }

    public List<GitRepository> getGit() {
        return git;
    }

    public void setGit(List<GitRepository> git) {
        this.git = git;
    }


    public GitHubRepositoryDetails getRepositoryDetails(String cloneUrl) {
        GitHubRepositoryDetails answer = github.getRepositoryDetails(cloneUrl);
        if (answer == null) {
            for (GitRepository gitRepository : git) {
                if (Objects.equal(cloneUrl, gitRepository.getCloneUrl())) {
                    answer = gitRepository.getRepositoryDetails();
                    if (answer != null) {
                        return answer;
                    }
                }
            }
        }
        return null;
    }

}
