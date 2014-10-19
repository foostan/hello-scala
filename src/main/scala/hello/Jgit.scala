package hello

import java.io.File

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.internal.storage.file.FileRepository
import org.eclipse.jgit.diff.DiffEntry

import collection.JavaConversions._

object Jgit {
  def main(args: Array[String]) {
    run()
  }

  def run() {
    val repository = new FileRepository(new File(".git"))
    val git = new Git(repository)
    val diffCommand = git.diff()
    val diffs = diffCommand.call()

    diffs.foreach { diff =>
      diff.getChangeType match {
        case DiffEntry.ChangeType.ADD =>
          println(diff.toString)
        case DiffEntry.ChangeType.MODIFY =>
          println(diff.toString)
        case DiffEntry.ChangeType.DELETE =>
          println(diff.toString)
        case DiffEntry.ChangeType.RENAME =>
          println(diff.toString)
        case DiffEntry.ChangeType.COPY =>
          println(diff.toString)
      }
    }
  }

}

