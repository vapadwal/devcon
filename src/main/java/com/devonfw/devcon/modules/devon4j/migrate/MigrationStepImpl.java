package com.devonfw.devcon.modules.devon4j.migrate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.devonfw.devcon.modules.devon4j.migrate.file.FileMigration;
import com.devonfw.devcon.modules.devon4j.migrate.version.VersionIdentifier;
import com.devonfw.devcon.output.Output;

/**
 * TODO hohwille This type ...
 *
 * @since 1.5.0
 */
public class MigrationStepImpl implements MigrationStep {

  private final Output output;

  private final VersionIdentifier from;

  private final VersionIdentifier to;

  private final List<FileMigration> fileMigrations;

  /**
   * The constructor.
   *
   * @param from
   * @param to
   */
  public MigrationStepImpl(Output output, VersionIdentifier from, VersionIdentifier to) {

    super();
    this.output = output;
    this.from = from;
    this.to = to;
    this.fileMigrations = new ArrayList<>();
  }

  @Override
  public void migrate(File projectFolder) throws Exception {

    visitFolder(projectFolder);
  }

  private void visit(File file) throws Exception {

    if (file.isFile()) {
      visitFile(file);
    } else if (file.isDirectory()) {
      visitFolder(file);
    } else {
      this.output.showError("File '%s' does not exist.", file.getAbsolutePath());
    }
  }

  private void visitFolder(File file) throws Exception {

    if (isIgnoredFolder(file)) {
      return;
    }
    for (File child : file.listFiles()) {
      visit(child);
    }
  }

  private boolean isIgnoredFolder(File file) {

    String name = file.getName();
    if (name.startsWith(".") && (name.length() > 1)) {
      return true;
    }
    if (name.equals("node_modules")) {
      return true;
    }
    return false;
  }

  /**
   * @param file
   */
  private void visitFile(File file) throws Exception {

    for (FileMigration fileMigration : this.fileMigrations) {
      fileMigration.migrate(file);
    }
  }

  @Override
  public VersionIdentifier getFrom() {

    return this.from;
  }

  @Override
  public VersionIdentifier getTo() {

    return this.to;
  }

  /**
   * @return fileMigrations
   */
  public List<FileMigration> getFileMigrations() {

    return this.fileMigrations;
  }

}
