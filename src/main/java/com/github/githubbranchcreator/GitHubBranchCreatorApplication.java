package com.github.githubbranchcreator;

import io.micrometer.observation.Observation;
import org.kohsuke.github.GHRef;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GitHubBranchCreatorApplication {

	private static final String GITHUB_ACCESS_TOKEN = "your-token";

	public static void main(String[] args) throws IOException {

		GitHub github = new GitHubBuilder().withOAuthToken(GITHUB_ACCESS_TOKEN).build();

		String[] repositories = {"username/repo1","username/repo2"};
		for (String repo : repositories) {
			GHRepository repository = github.getRepository(repo);
			GHRef masterRef = repository.getRef("heads/master");
			repository.createRef("refs/heads/my-new-branch-2", masterRef.getObject().getSha());
			System.out.println("Branch created in " + repo);
		}

	}

}
