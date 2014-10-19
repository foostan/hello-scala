package hello

import java.io.{ByteArrayOutputStream, File}

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.internal.storage.file.FileRepository
import org.eclipse.jgit.diff.{DiffFormatter, DiffEntry}
import org.eclipse.jgit.treewalk.CanonicalTreeParser
import org.eclipse.jgit.util.FileUtils

import collection.JavaConversions._

object Jgit {
  def main(args: Array[String]) {
    run()
  }

  def run() {
    val repository = new FileRepository(new File(".git"))
    val git = new Git(repository)

    /*
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
    */



    val headId = git.getRepository.resolve("HEAD^{tree}")
    val oldId = git.getRepository.resolve("HEAD^^{tree}")

    val reader = git.getRepository.newObjectReader()

    val oldTreeIter = new CanonicalTreeParser()
    oldTreeIter.reset(reader, oldId)
    val newTreeIter = new CanonicalTreeParser()
    newTreeIter.reset(reader, headId)

    val diffs = git.diff()
      .setNewTree(newTreeIter)
      .setOldTree(oldTreeIter)
      .call()

    val out = new ByteArrayOutputStream()
    val df = new DiffFormatter(out)
    df.setRepository(git.getRepository)

    diffs.foreach { diff =>
      df.format(diff)
      diff.getOldId
      val diffText = out.toString("UTF-8")
      System.out.println(diffText)
      out.reset()
    }
  }
}

