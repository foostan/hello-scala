package hello

import java.io.{ByteArrayOutputStream, File}

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.internal.storage.file.FileRepository
import org.eclipse.jgit.diff.{DiffFormatter, DiffEntry}
import org.eclipse.jgit.lib.ObjectReader
import org.eclipse.jgit.treewalk.{AbstractTreeIterator, WorkingTreeIterator, CanonicalTreeParser}
import org.eclipse.jgit.util.FileUtils

import collection.JavaConversions._

object Jgit {
  def main(args: Array[String]) {
    run()
  }

  def run() {
    val repository = new FileRepository(new File("tmp/.git"))
    val git = new Git(repository)

    val out = new ByteArrayOutputStream()
    val df = new DiffFormatter(out)
    df.setRepository(repository)

    val diffCommand = git.diff()
    val diffs = diffCommand.call()

    diffs.foreach { diff =>
      diff.getChangeType match {
        case DiffEntry.ChangeType.ADD =>
          println(diff.toString)
        case DiffEntry.ChangeType.MODIFY =>
          println(diff.toString)

          df.format(diff)
          println(out.toString)
          out.reset()

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

