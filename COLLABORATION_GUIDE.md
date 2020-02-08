**How to add your changes to the project's repository**\
In order to maintain a clear scope & progress of the project at hand, please make sure to follow the instructions presented in this document before pushing any changes to remote branches.

**1. Project Overview**\
To maintain a clean workspace for the team, the project has been structured based on the components (or subprojects) that sustain ongoing development/maintenance. That is why, for each of the components there is a main directory which holds the source code.

**Project Structure:**
```
Tracker_v2 -----------	/android/	- Android app source code
		|-----	/iOS/		- iOS app source code
		|-----	/web/		- Web app source code
		|-----	/ws/		- Backend source code
		|-----	/docs/		- Design docs, files & assets
```

Based on the structure above, every new feature related to some component of the project should be stored in its related sub-project directory based on the rules and guidelines provided by each single sub-project's README file (this .md file should be present in every single sub-project, containing valuable info on the matter at hand).

**2. Branching & Branch names**\
The main development branch is the **master** branch. Usually, you should create your new branches from this one in order to start working on a feature/bugfix.
In order to avoid merge conflicts and working on outdated source code, please make sure to pull the master branch into your local/remote tracking branch **at least** once per day.
Since there might be people using GUI systems for their Git interaction, please consider maintaining a clean formatting of your branches' names. eg: `ws/feature/mustHaveImplementationBranch` or `android/bugfix/12` (where `12` is the issue's ID)

**Note:** Please make sure your branch names follow a directory-style format and ends in a unique and self-explanatory tag/name, such as a *ticket title*, *issue ID*, *main scope*, etc.

Also, **make sure to delete your (remote) branch after merging its related Pull Requests** (see the PR section for Pull Requests guidelines), so that the repository won't be clustered with stale branches.

A standalone branch should handle **1 and only 1 feature/issue at a time**; this is very important for keeping the commit history as clear as possible in order to enable future rollbacks with minimal difficulty, if required. Following the *KISS* standards (*Keep It Simple, Stupid*), the more features are handled in the same branch, the more time will be required for reviewing the changes when creating a PR later on, the more chances are that the PR will be declined in the end; basically, *try to keep your branch focused on the development of a single feature*, creating additional branches for additional features/subfeatures if necessary.

**3. Pull Requests & Pull Requests Approval Process**\
**-> Creating PRs**\
The development process for this project should be crystal clear and easy to pick up by any future developer that wishes to collaborate. Also, the main objective of this project is learning new technologies and other useful stuff that we could use in both personal and professiona projects. In order for that to happen, whenever a dev decides to merge some new code in the project's codebase, the changes should be included in a **Pull Request (PR)** which should follow the [Pull Request Structure Template](../v0_0_1_prim/PULL_REQUEST_STRUCTURE_TEMPLATE.md).

Yet again, **avoid opening huge PRs** at this is not recommended in order to reduce the time that PRs are undergoing review.

After making sure that the PR Structure Template has been followed, the owner of the PR should add any component/feature's owners to the reviewers list, where available. (the ownership of the components/features should be documented in separate `.owners` files later on)
**Note:** The reviewers list does not restrict the owner to the owners in the `.owners` files, and can also add any other peer as long as that person can benefit from reviewing the changes, or could provide valuable comments on the open PR; the users mentioned in the `.owners` files though, must be included in the PR's reviewers list.

**-> Resolving a PR**\
While a PR is undergoing review, the reviewers can comment on the changes that the PR owner has included, enabling collaborators to decide whether additional changes should be made or not. A PR shall only be merged if all the comments/tasks/discussions have been cleared/completed. While addressing the comments on the PR, the owner can add, edit, remove commits based on the aquired feedback, keeping the PR open for as long as necessary. If the owner fails to address the comments or the PR is considered to be adding faulty code (error prone), the PR can be closed without merging to the destination branch, requiring the owner to open a new PR after taking the required steps.

**Note:** Whenever merging PRs, **make sure to squash the commits** included in the PR in order to maintain a clean commit history.
