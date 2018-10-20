package com.devonfw.devcon.modules.devon4j.migrate.builder;

import com.devonfw.devcon.modules.devon4j.migrate.file.XmlFileMigration;
import com.devonfw.devcon.modules.devon4j.migrate.version.VersionIdentifier;
import com.devonfw.devcon.modules.devon4j.migrate.xml.MavenDependencyReplacement;
import com.devonfw.devcon.modules.devon4j.migrate.xml.MavenPropertyReplacement;
import com.devonfw.devcon.modules.devon4j.migrate.xml.XmlStringReplacement;

/**
 * {@link XmlMigrationBuilder} for POM files.
 */
public class PomXmlMigrationBuilder extends XmlMigrationBuilder {

  /**
   * The constructor.
   *
   * @param parent the parent builder.
   */
  public PomXmlMigrationBuilder(MigrationStepBuilder parent) {

    super(parent, XmlFileMigration.POM_XML_PATTERN);
  }

  /**
   * @param pattern the {@link VersionIdentifier} for the dependency to match and replace.
   * @param replacement the {@link VersionIdentifier} with the replacement dependency.
   * @return {@code this}.
   */
  public PomXmlMigrationBuilder replaceDependency(VersionIdentifier pattern, VersionIdentifier replacement) {

    this.migration.getMigrations().add(new MavenDependencyReplacement(pattern, replacement));
    return this;
  }

  /**
   * @param property the name of the Maven property to replace.
   * @param value the new value of the specified property.
   * @return {@code this}.
   */
  public PomXmlMigrationBuilder replaceProperty(String property, String value) {

    return replaceProperty(property, value, property);
  }

  /**
   * @param property the name of the Maven property to replace.
   * @param value the new value of the specified property.
   * @param newProperty the new name of the Maven property to replace.
   * @return {@code this}.
   */
  public PomXmlMigrationBuilder replaceProperty(String property, String value, String newProperty) {

    this.migration.getMigrations().add(new MavenPropertyReplacement(property, value, newProperty));
    return this;
  }

  /**
   * @param search the plain {@link String} to replace in POM.
   * @param replacement the replacement {@link String}.
   * @return {@code this}.
   */
  public PomXmlMigrationBuilder replaceString(String search, String replacement) {

    this.migration.getMigrations().add(new XmlStringReplacement(search, replacement));
    return this;
  }

}
